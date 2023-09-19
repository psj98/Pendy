import React, { useState } from 'react';
import './BankMainTemplate.css';
import { useNavigate } from 'react-router-dom';

const BankMainTemplate = () => {
  const accountInfoJSON = sessionStorage.getItem('accountList');
  const navigate = useNavigate();
  let accountInfo = [];

  if (accountInfoJSON) {
    accountInfo = JSON.parse(accountInfoJSON);
  }

  const [selectedAccountIndex, setSelectedAccountIndex] = useState(0);
  const selectedAccount = accountInfo[selectedAccountIndex];

  const handleAccountClick = (index) => {
    setSelectedAccountIndex(index);
  };

  //계좌 내역 확인 페이지로 이동
  const onTransListClick = () => {
    navigate(`account/${selectedAccount.accountNumber}`);
  };

  //계좌 내역 생성 페이지로 이동
  const onTransRegistClick = () => {
    navigate(`regist/${selectedAccount.accountNumber}`);
  };

  return (
    <div className="bank-container">
      <h2>계좌 리스트</h2>
      <div className="bank-input-container">
        {accountInfo.map((account, index) => (
          <div
            key={index}
            className={`bank-input ${
              selectedAccountIndex === index ? 'selected' : ''
            }`}
            onClick={() => handleAccountClick(index)}
          >
            <div className="bank-input1">
              <input
                type="text"
                className="input"
                placeholder={`은행`}
                variant="outlined"
                value={account.bankCode}
                readOnly
              />
            </div>
            <div className="bank-input2">
              <input
                type="text"
                className="input"
                placeholder={`은행`}
                variant="outlined"
                value={account.accountNumber}
                readOnly
              />
            </div>
          </div>
        ))}
      </div>
      <div className="selected-account-info">
        <h3>선택한 계좌 정보</h3>
        <p>은행: {selectedAccount ? selectedAccount.bankCode : ''}</p>
        <p>계좌번호: {selectedAccount ? selectedAccount.accountNumber : ''}</p>
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
