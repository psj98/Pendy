import React from 'react';
import './BankMainTemplate.css';
import { useNavigate } from 'react-router-dom';

const BankMainTemplate = () => {
  const accountNumber = sessionStorage.getItem('accountNumber');
  const bankCode = sessionStorage.getItem('bankCode');

  const navigate = useNavigate();

  //계좌 내역 확인 페이지로 이동
  const onTransListClick = () => {
    navigate(`account/${accountNumber}`);
  };

  //계좌 내역 생성 페이지로 이동
  const onTransRegistClick = () => {
    navigate(`regist/${accountNumber}`);
  };

  return (
    <div className="bank-container">
      <div className="selected-account-info">
        <p>
          은행: {bankCode}&nbsp;&nbsp;&nbsp; 계좌번호: {accountNumber}
        </p>
      </div>
      <div className="bank-button-container">
        <button className="bank-button" onClick={onTransListClick}>
          계좌 내역 확인하기
        </button>
        <br />
        <button className="bank-button" onClick={onTransRegistClick}>
          사용 내역 등록하기
        </button>
      </div>
    </div>
  );
};

export default BankMainTemplate;
