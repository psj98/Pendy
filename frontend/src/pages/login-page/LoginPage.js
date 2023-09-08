import React from 'react';
import './LoginPage.css';
import { Outlet } from 'react-router-dom';

const LoginPage = () => {
  return (
    <div>
      <div>로그인 및 회원가입</div>
      <Outlet />
    </div>
  );
};

export default LoginPage;
