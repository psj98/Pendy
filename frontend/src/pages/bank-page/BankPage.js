import React from 'react';
import './BankPage.css';
import { Outlet } from 'react-router-dom';
import useBankLogin from '../../hooks/useBankLogin';
import BankHeader from '../../components/common/bank-header/BankHeader';

const BankPage = () => {
  const isBankLoggedIn = useBankLogin();

  return (
    <div className="bank-page">
      <BankHeader isBankLoggedIn={isBankLoggedIn} />
      <div className="header-save" />
      <Outlet />
    </div>
  );
};

export default BankPage;
