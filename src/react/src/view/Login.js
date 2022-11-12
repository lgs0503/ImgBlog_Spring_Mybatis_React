import {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router";

function Login() {
    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const login = () => {
        console.log({
            userId,
            password
        });

        console.log(validationChk());

        if(validationChk()){

            axios.post('http://localhost:8080/login', {
                userId,
                password
            })
                .then((result)=>{
                    console.log(result.data.count)
                    if (result.data.count > 0) {
                        navigate('/')
                    } else {
                        window.alert('아이디와 패스워드를 확인해주세요')
                    }
                }).catch((Error)=>{
                console.log(Error)
            });



        }
    }

    const validationChk = () => {
        if(!userId) {
            window.alert('아이디를 입력해');
            return false;
        }

        if(!password) {
            window.alert('비밀번호를 입력해');
            return false;
        }

        return true;
    }

    return (
        <div className="Login">
            <div>
                <div>
                    <input type={"text"} value={userId} placeholder={'아이디를 입력하시던가'} onChange={(e) => {
                        setUserId(e.target.value)
                    }}/>
                    <input type={"text"} value={password} placeholder={'비밀번호를 입력하시던가'}onChange={(e) => {
                        setPassword(e.target.value)
                    }}/>
                </div>
                <div>
                    <button onClick={() => login()}>로그인</button>
                </div>
            </div>
        </div>
    );
}

export default Login;
