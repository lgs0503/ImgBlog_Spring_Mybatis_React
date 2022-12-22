package com.comm.file.controller;

import com.comm.util.CommonUtil;
import com.comm.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FileController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final int FILE_WRITE_ERROR = -1;
    private final int FILE_MAX_BYTE = 1024;

    @Autowired
    String uploadPath;

    @Autowired
    FileService fileService;

    /**
     *  파일 업로드
     */
    @Transactional
    @RequestMapping(value="/fileUpload", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> fileUpload(MultipartHttpServletRequest request){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<MultipartFile> fileList = request.getFiles("file");

            /** 경로 폴더 없을 경우 생성 **/
            CommonUtil.makeDirectories(uploadPath);

            /** 업로드 완료된 파일정보 를 담을 리스트 객체 **/
            List<Map<String, Object>> uploadList = new ArrayList<Map<String, Object>>();

            /** 여러개 파일을 한번에 업로드 하기위해 반복 진행 **/
            for (MultipartFile mf : fileList) {

                /** 업로드 파일 정보 DB 저장 **/
                Map<String, Object> fileMap = saveFile(mf);

                String uploadFileName = (String) CommonUtil.notValue(fileMap.get("saveFileName"), "")
                        + "." + (String) CommonUtil.notValue(fileMap.get("fileExten"), "");

                /** 파일 생성 **/
                createFile(mf, uploadFileName);

                uploadList.add(fileMap);
            }

            /** 업로드 완료된 파일정보들 반환 **/
            result.put("uploadList", uploadList);

        } catch (Exception e){
            log.error("fileUpload Exception", e);
        }

        return result;
    }

    /**
     *  DB에 업로드 파일 정보 저장
     */
    private Map<String, Object> saveFile(MultipartFile mf) {

        Map<String, Object> fileMap = new HashMap<String, Object>();

        try {
            long time = System.currentTimeMillis();

            String originFileName = mf.getOriginalFilename();
            String fileExten = originFileName.substring(originFileName.lastIndexOf(".") + 1);
            String fileName = originFileName.replace(fileExten,"").replace(".","");

            String saveFileName = String.format("%d_%s", time, fileName);

            /** DB FILE TABLE 저장 **/
            fileMap.put("filePath", uploadPath);
            fileMap.put("fileName", fileName);
            fileMap.put("filePhysicalName", saveFileName);
            fileMap.put("fileSize", mf.getSize());
            fileMap.put("fileExten", fileExten);

            fileService.insertFile(fileMap);

        } catch (Exception e) {
            log.error("saveFile Exception", e);
        }
        return fileMap;
    }

    /**
     *  파일업로드 경로에 파일 생성
     */
    private void createFile(MultipartFile mf, String uploadFileName){
        try {
            mf.transferTo(new File(uploadPath, uploadFileName));
        } catch (Exception e) {
            log.error("createFile Exception", e);
        }
    }

    /**
     *  파일 다운로드
     */
    @RequestMapping(value="/fileDownload", method= RequestMethod.GET)
    public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {

            /** 파일 번호로 상세 정보 조회 **/
            String fileNo = request.getParameter("fileNo");

            Map<String, Object> fileMap = new HashMap<String, Object>();
            fileMap.put("fileNo", fileNo);

            Map<String, Object> fileResult = fileService.getFile(fileMap);

            String filePhysicalName = (String) CommonUtil.notValue(fileResult.get("filePhysicalName"), "");
            String fileExten = (String) CommonUtil.notValue(fileResult.get("fileExten"), "");
            String fileName = (String) CommonUtil.notValue(fileResult.get("fileName"), "");
            String fileNameExten = fileName + '.' + fileExten;

            /** 헤더설정 - 한국어 파일명 **/
            setKoreanHeader(request, response, fileNameExten);

            /** 업로드 경로 + 파일명 + 파일확장자 **/
            String path = uploadPath + "/" + filePhysicalName + '.' + fileExten;

            File file = new File(path);

            /** 파일다운로드 **/
            FileDownload(response, file);
        } catch (Exception e) {
            log.error("fileDownload Exception", e);
        }
    }

    /**
     *  다운로드 한글파일명 설정
     */
    private void setKoreanHeader(HttpServletRequest request, HttpServletResponse response, String fileNameExten){

        try {
            String header = request.getHeader("User-Agent");

            if (header.contains("MSIE") || header.contains("Trident")) {
                fileNameExten = URLEncoder.encode(fileNameExten,"UTF-8").replaceAll("\\+", "%20");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileNameExten + ";");
            } else {
                fileNameExten = new String(fileNameExten.getBytes("UTF-8"), "ISO-8859-1");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileNameExten + "\"");
            }
        } catch(Exception e) {
            log.error("setKoreanHeader Exception", e);
        }
    }

    /**
     *  파일 다운로드 로직
     */
    private void FileDownload(HttpServletResponse response, File file){

        try {
            /** Response Download로 설정 **/
            setDownloadResponse(response, file);

            /** 파일 읽어오기 **/
            FileInputStream fileInputStream = new FileInputStream(file);

            OutputStream out = response.getOutputStream();

            /** 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음 **/
            int read = 0;
            byte[] buffer = new byte[FILE_MAX_BYTE];

            while ((read = fileInputStream.read(buffer)) != FILE_WRITE_ERROR) {
                out.write(buffer, 0, read);
            }

            response.flushBuffer();
            fileInputStream.close();
        } catch (Exception e){
            log.error("FileDownload Exception", e);
        }
    }

    /**
     *  다운로드 Response 헤더 설정
     */
    private void setDownloadResponse(HttpServletResponse response, File file){

        try {
            int bytes = (int)file.length();

            response.setContentType( "application/download; UTF-8" );
            response.setContentLength(bytes);
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Transfer-Encoding", "binary;");
            response.setHeader("Pragma", "no-cache;");
            response.setHeader("Expires", "-1;");
        } catch(Exception e){
            log.error("setDownloadResponse Exception", e);
        }
    }

    /**
     *  이미지 데이터 조회 base64(섬네일용)
     */
    @RequestMapping(value = "/getImageData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getImageData(@RequestBody Map<String, Object> fileParamMap) {

        Map<String, Object> resultImageDate = new HashMap<String, Object>();

        try {

            Map<String, Object> fileMap = fileService.getFile(fileParamMap);

            String filePhysicalName = (String) CommonUtil.notValue(fileMap.get("filePhysicalName"), "");
            String fileExten = (String) CommonUtil.notValue(fileMap.get("fileExten"), "");

            /* 업로드 경로 + 파일명 + 파일확장자*/
            String path = uploadPath + "/" + filePhysicalName + '.' + fileExten;

            File file = new File(path);

            resultImageDate.put("imageData", CommonUtil.fileToBase64(file));

        } catch (Exception e){
            log.error("getImageData Exception", e);
        }

        return resultImageDate;
    }
}
