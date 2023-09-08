import React from 'react';
import './LoginPage.css';
import Header from '../../components/common/header/Header';
import { Outlet } from 'react-router-dom';

const LoginPage = () => {
  return (
    <div className="login-page">
      <Header />
      <div className="header-save" />
      <Outlet />
    </div>
  );
};

export default LoginPage;
