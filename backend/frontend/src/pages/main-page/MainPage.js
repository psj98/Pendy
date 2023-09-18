import React from 'react';
import './MainPage.css';
import Header from '../../components/common/header/Header';
import { Outlet } from 'react-router-dom';

const MainPage = ({ isLoggedIn }) => {
  return (
    <div className="main-page">
      <Header isLoggedIn={isLoggedIn} />
      <div className="header-save" />
      <Outlet />
    </div>
  );
};

export default MainPage;
