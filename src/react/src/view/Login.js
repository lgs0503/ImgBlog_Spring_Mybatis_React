import {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router";

function Login() {
    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const login = async () => {
        console.log({
            userId,
            password
        });

        console.log(validationChk());

        if(validationChk()){

            const result = await axios.post('http://localhost:8080/login', {
                userId,
                password
            });

            console.log(result.data.loginResult);

            if (result.data.loginResult === true) {
                navigate('/')
            } else {
                window.alert('아이디와 패스워드를 확인해주세요')
            }

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

    const registerView = () => {
        navigate('/register');
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
                    <button onClick={() => registerView()}>회원가입</button>
                </div>
            </div>
        </div>
    );
}

export default Login;
