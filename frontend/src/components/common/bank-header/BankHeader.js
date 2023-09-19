import React from 'react';
import './BankHeader.css';
import { Link, useNavigate } from 'react-router-dom';

// 페이지 헤더
const BankHeader = ({ isBankLoggedIn }) => {
  const navigate = useNavigate();

  const renderLoginOrLogout = () => {
    if (isBankLoggedIn) {
      return (
        <div onClick={onLogout} className="bank-header-link-content">
          로그아웃
        </div>
      );
    } else {
      return (
        <Link to={'/bank/login'} className="bank-header-link-content">
          로그인
        </Link>
      );
    }
  };

  const onLogout = () => {
    console.log('bankLogout');
    sessionStorage.removeItem('accountNumber');
    sessionStorage.removeItem('balance');
    sessionStorage.removeItem('bankCode');
    navigate('/bank', { replace: true });
  };

  return (
    <div className="bank-header">
      <div className="bank-header-logo-container">
        <Link to={'/bank'} className="bank-header-link-content">
          PENDY BANK
        </Link>
      </div>
      <div className="bank-header-blank"></div>
      <div className="bank-header-menu-container">{renderLoginOrLogout()}</div>
    </div>
  );
};

export default BankHeader;
