import React, { useState } from 'react';
import './LoginTemplate.css';
import handleLogin from '../../utils/handleLogin';
import handleCheckGoal from '../../utils/handleCheckGoal';
import { Link, useNavigate } from 'react-router-dom';
import { FaUser, FaLock } from 'react-icons/fa';

const LoginTemplate = () => {
  const [state, setState] = useState({ email: '', password: '' });
  const navigate = useNavigate();

  //로그인 버튼 동작
  const onLoginButtonClick = async (event) => {
    event.preventDefault();
    try {
      const response = await handleLogin(state.email, state.password);
      console.log(response);
      if (response.data.code === 1000) {
        const accountList = JSON.stringify(
          response.data.data.accountListResponseDtoList,
        );
        localStorage.setItem('accessToken', response.data.data.accessToken);
        sessionStorage.setItem('email', response.data.data.email);
        sessionStorage.setItem('name', response.data.data.name);
        sessionStorage.setItem('age', response.data.data.age);
        sessionStorage.setItem('salary', response.data.data.salary);
        sessionStorage.setItem('accountList', accountList);

        alert('로그인에 성공하셨습니다.');

        const goalResponse = handleCheckGoal();
        goalResponse.then((result) => {
          const isCheckGoal = result;
          if (isCheckGoal) {
            navigate('/', { replace: true });
          } else {
            navigate('/goal', { replace: true });
          }
        });
      } else {
        console.error(response.data.code + ' ' + response.data.message);
        alert('로그인에 실패하셨습니다');
        setState({ email: '', password: '' });
      }
    } catch (error) {
      console.error('Login failed');
      alert('알 수 없는 이유로 로그인에 실패하셨습니다');
      setState({ email: '', password: '' });
    }
  };

  // Enter 키를 누른 경우 onLoginButtonClick 함수 실행
  const onPressEnterKey = (event) => {
    if (event.key === 'Enter') {
      event.preventDefault();

      if (state.email === '') {
        alert('이메일을 입력해주세요');
        return;
      }

      if (state.password === '') {
        alert('비밀번호를 입력해주세요');
        return;
      }

      onLoginButtonClick(event);
    }
  };

  return (
    <div className="login">
      <h1 className="login-title">로그인</h1>

      <div className="login-container-box">
        {/* 이메일 입력 */}
        <div className="login-input email-input">
          <FaUser className="login-icon" />
          <input
            className="login-input-box email-box"
            placeholder="이메일"
            variant="outlined"
            value={state.email}
            onChange={(e) => setState({ ...state, email: e.target.value })}
            onKeyDown={onPressEnterKey}
          />
        </div>

        {/* 비밀번호 입력 */}
        <div className="login-input pw-input">
          <FaLock className="login-icon" />
          <input
            type="password"
            className="login-input-box pw-box"
            placeholder="비밀번호"
            variant="outlined"
            value={state.password}
            onChange={(e) => setState({ ...state, password: e.target.value })}
            onKeyDown={onPressEnterKey}
          />
        </div>

        {/* 로그인 버튼 */}
        <div className="login-input login-button-div">
          <button className="login-button" onClick={onLoginButtonClick}>
            로그인
          </button>
        </div>
      </div>

      {/* 비밀번호 찾기, 회원가입 링크 */}
      <div className="login-pw-sign-up-container">
        <div className="login-input sign-up-pw-button">
          <Link to="/login/signup" className="login-link">
            회원가입
          </Link>
        </div>
        <div className="login-link">|</div>
        <div className="login-input sign-up-pw-button">
          <Link to="/login/repassword" className="login-link">
            비밀번호 찾기
          </Link>
        </div>
      </div>
    </div>
  );
};

export default LoginTemplate;
