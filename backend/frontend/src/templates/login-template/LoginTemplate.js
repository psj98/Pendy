import React, { useState } from 'react';
import './LoginTemplate.css';
import handleLogin from '../../utils/handleLogin';
import { Link, useNavigate } from 'react-router-dom';

const LoginTemplate = () => {
  const [state, setState] = useState({ email: '', password: '' });
  const navigate = useNavigate();

  //로그인 버튼 동작
  const onLoginButtonClick = async (event) => {
    event.preventDefault();
    try {
      //eslint-disable-next-line
      const response = await handleLogin(state.email, state.password);
      localStorage.setItem('accessToken', response.data.data.accessToken);
      sessionStorage.setItem('email', response.data.data.email);
      sessionStorage.setItem('name', response.data.data.name);
      sessionStorage.setItem('age', response.data.data.age);
      sessionStorage.setItem('salary', response.data.data.salary);
      console.log('Login success');
      alert('로그인에 성공하셨습니다.');
      navigate('/', { replace: true });
    } catch (error) {
      console.error('Login failed');
      alert('로그인에 실패하셨습니다.');
      setState({ email: '', password: '' });
    }
  };

  return (
    <div className="login">
      <h2 className="login-title">PENDY</h2>

      {/* 이메일 입력 */}
      <div className="login-input">
        <input
          className="input"
          placeholder="이메일"
          variant="outlined"
          value={state.email}
          onChange={(e) => setState({ ...state, email: e.target.value })}
        />
        <span className="border"></span>
      </div>

      {/* 비밀번호 입력 */}
      <div className="login-input">
        <input
          type="password"
          className="input"
          placeholder="비밀번호"
          variant="outlined"
          value={state.password}
          onChange={(e) => setState({ ...state, password: e.target.value })}
        />
        <span className="border"></span>
      </div>
      <br />

      {/* 로그인 버튼 */}
      <div className="login-input">
        <button className="login-button" onClick={onLoginButtonClick}>
          로그인
        </button>
      </div>

      {/* 비밀번호 찾기, 회원가입 링크 */}
      <div className="login-input">
        <Link to="/login/signup" className="login-link">
          회원가입
        </Link>
      </div>
      <div className="login-input">
        <Link to="/login/repassword" className="login-link">
          비밀번호 찾기
        </Link>
      </div>
    </div>
  );
};

export default LoginTemplate;