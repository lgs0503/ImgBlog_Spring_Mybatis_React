import {useForm} from "react-hook-form";
import {useState} from "react";
import axios from "axios";

function Register() {

    const {register, handleSubmit, formState:{isSubmitting, isDirty, errors}} = useForm();
    const [passwordChkMsg, setPasswordChkMsg] = useState(false);


    return (
        <div className="Register">
            <form onSubmit={handleSubmit(async (data) => {
                setPasswordChkMsg(false);
                await new Promise((r) => {setTimeout(r, 1000)});
                console.log(JSON.stringify(data));

                /** 비밀번호 비밀번호확인 유효성 체크 **/
                if (data.password !== data.passwordChk) {
                    setPasswordChkMsg(true);
                    return;
                }

                const result = await axios.post('http://localhost:8080/register', data);
            })}>
                <div>
                    <div>
                        <input type={"text"}
                               placeholder={"아이디를 입력해주세요"}
                               id={"userId"}
                               aria-invalid={!isDirty ? undefined : errors.userId ? "true" : "false"}
                               {...register("userId",{
                                   required: "아이디는 필수 입력입니다."
                               })}
                        />
                        {
                            errors.userId && <small role="alert">{errors.userId.message}</small>
                        }
                    </div>
                    <div>
                        <input type={"password"}
                               placeholder={"비밀번호를 입력해주세요"}
                               id={"password"}
                               aria-invalid={!isDirty ? undefined : errors.password ? "true" : "false"}
                               {...register("password",{
                                   required: "비밀번호는 필수 입력입니다."
                               })}
                        />
                        {
                            errors.password && <small role="alert">{errors.password.message}</small>
                        }
                    </div>
                    <div>
                        <input type={"password"}
                               placeholder={"비밀번호를 입력해주세요"}
                               id={"passwordChk"}
                               aria-invalid={!isDirty ? undefined : errors.passwordChk ? "true" : "false"}
                               {...register("passwordChk", {
                                   required: "비밀번호를 다시 입력해주세요."
                               })}
                        />
                        {errors.passwordChk && <small role={"alert"}>{errors.passwordChk.message}</small>}
                        {
                            passwordChkMsg &&
                            <small role={"alert"}>비밀번호와 비밀번호 확인을 일치하게 입력해주세요.</small>
                        }
                    </div>
                    <div>
                        <input type={"text"}
                               placeholder={"이름을 입력해주세요"}
                               id={"name"}
                               aria-invalid={!isDirty ? undefined : errors.name ? "true" : "false"}
                               {...register("name", {
                                   required: "이름은 필수 입력입니다."
                               })}/>
                        {errors.name && <small role={"alert"}>{errors.name.message}</small>}
                    </div>
                    <div>
                        <select placeholder={"성별를 선택해주세요"}
                                id={"gender"}
                                aria-invalid={!isDirty ? undefined : errors.gender ? "true" : "false"}
                                {...register("gender", {
                                    required: "비밀번호를 다시 입력해주세요."
                                })}>
                            <option value={"1"}>남</option>
                            <option value={"2"}>여</option>
                        </select>
                        {errors.gender && <small role={"alert"}>{errors.gender.message}</small>}
                    </div>
                    <div>
                        <input type={"date"}
                               placeholder={"생년월일을 입력해주세요"}
                               id={"birthday"}
                               aria-invalid={!isDirty ? undefined : errors.birthday ? "true" : "false"}
                               {...register("birthday", {
                                   required: "생년월일는 필수 입력입니다."
                               })}/>
                        {errors.birthday && <small role={"alert"}>{errors.birthday.message}</small>}
                    </div>
                    <div>
                        <input type={"email"}
                               placeholder={"이메일를 입력해주세요"}
                               id={"email"}
                               {...register("email")}/>
                    </div>
                    <div>
                        <input type={"text"}
                               placeholder={"주소를 입력해주세요"}
                               id={"location"}
                               {...register("location")}/>
                    </div>
                    <div>
                        <input type={"text"}
                               placeholder={"상세주소를 입력해주세요"}
                               id={"locationDtl"}
                               {...register("locationDtl")}/>
                    </div>
                    <div>
                        <input type={"text"}
                               placeholder={"연락처를 입력해주세요"}
                               id={"phoneNum"}
                               {...register("phoneNum")}/>
                    </div>
                </div>
                <button type={"submit"} disabled={isSubmitting}>
                    회원가입
                </button>
            </form>
        </div>
    );
}

export default Register;
