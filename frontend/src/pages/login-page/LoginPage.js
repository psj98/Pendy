import React from 'react';
import './LoginPage.css';
import Header from '../../components/common/header/Header';
import { Outlet, useNavigate } from 'react-router-dom';

const LoginPage = ({ isLoggedIn }) => {
  const navigate = useNavigate();
  if (isLoggedIn) {
    navigate('/', { replace: true });
  }
  return (
    <div className="login-page">
      <Header />
      <div className="header-save" />
      <Outlet />
    </div>
  );
};

export default LoginPage;
