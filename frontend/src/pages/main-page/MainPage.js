import React from 'react';
import './MainPage.css';
import { Outlet } from 'react-router-dom';
import Header from '../../components/common/header/Header';

const MainPage = () => {
  return (
    <div>
      <Header />
      <div className="header-save" />
      <Outlet />
    </div>
  );
};

export default MainPage;
