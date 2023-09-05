import React from 'react';
import './MainPage.css';
import { Outlet } from 'react-router-dom';

const MainPage = () => {
  return (
    <div>
      <p>메인 페이지입니다.</p>
      <Outlet />
    </div>
  );
};

export default MainPage;
