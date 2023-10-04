// 계좌 페이지용 헤더
import React from 'react';
import './BankHeader.css';
import { Link, useNavigate } from 'react-router-dom';

const BankHeader = ({ isBankLoggedIn }) => {
  const navigate = useNavigate();

  //로그아웃 클릭 시 동작
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
      <div className="bank-header-blank" />
      <div className="bank-header-menu-container">
        {isBankLoggedIn ? (
          <div onClick={onLogout} className="bank-header-link-content">
            로그아웃
          </div>
        ) : (
          <Link to={'/bank/login'} className="bank-header-link-content">
            로그인
          </Link>
        )}
      </div>
    </div>
  );
};

export default BankHeader;
