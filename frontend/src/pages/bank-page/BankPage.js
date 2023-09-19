import React from 'react';
import './BankPage.css';
import { Outlet } from 'react-router-dom';

const BankPage = () => {
  return (
    <div>
      <Outlet />
    </div>
  );
};

export default BankPage;
