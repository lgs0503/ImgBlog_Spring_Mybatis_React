package com.imgBlog.comm.file.controller;

import com.imgBlog.comm.file.service.FileService;
import com.imgBlog.comm.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FileController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    String uploadPath;

    @Autowired
    FileService fileService;

    /**
     *  파일 업로드
     * @param request 업로드 파일 정보를 담은 request
     * @return ModelAndView 업로드 파일 정보 및 업로드 성공여부
     */
    @Transactional
    @RequestMapping(value="/file/upload", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> fileUpload(MultipartHttpServletRequest request){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<MultipartFile> fileList = request.getFiles("file");

            /* 경로 폴더 없을 경우 생성 */
            CommonUtil.makeDirectories(uploadPath);

            long time = System.currentTimeMillis();

            /* 업로드 완료된 파일정보 를 담을 리스트 객체 */
            List<Map<String, Object>> uploadList = new ArrayList<Map<String, Object>>();

            /* 여러개 파일을 한번에 업로드 하기위해 반복 진행 */
            for (MultipartFile mf : fileList) {

                Map<String, Object> fileMap = new HashMap<String, Object>();

                String originFileName = mf.getOriginalFilename();
                String fileExten = originFileName.substring(originFileName.lastIndexOf(".") + 1);
                String fileName = originFileName.replace(fileExten,"").replace(".","");

                String saveFileName = String.format("%d_%s", time, fileName);

                /* DB FILE TABLE 저장 */
                fileMap.put("filePath", uploadPath);
                fileMap.put("fileName", fileName);
                fileMap.put("filePhysicalName", saveFileName);
                fileMap.put("fileSize", mf.getSize());
                fileMap.put("fileExten", fileExten);

                fileService.insertFile(fileMap);

                /* 파일 생성 */
                try {
                    mf.transferTo(new File(uploadPath, saveFileName + '.' + fileExten));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                uploadList.add(fileMap);
            }

            /* 업로드 완료된 파일정보들 반환 */
            result.put("uploadList", uploadList);

        } catch (Exception e){
            log.error("fileUpload Exception", e);
        }

        return result;
    }

}
