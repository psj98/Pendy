import React from 'react';
import './AccountTemplate.css';
import { useParams } from 'react-router-dom';
import useTransList from '../../hooks/useTransList';

const formatDate = (dateString) => {
  const date = new Date(dateString);
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const day = date.getDate().toString().padStart(2, '0');
  return `${month}/${day}`;
};

const formatTime = (dateString) => {
  const date = new Date(dateString);
  const hours = date.getHours().toString().padStart(2, '0');
  const minutes = date.getMinutes().toString().padStart(2, '0');
  return `${hours}:${minutes}`;
};

const getTransactionTypeText = (transactionType) => {
  return transactionType === 1 ? '입금' : '출금';
};

const AccountTemplate = () => {
  const { accountNumber } = useParams();
  const { transList, loading } = useTransList(accountNumber);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div className="account-template">
      <h1>계좌 번호: {accountNumber}</h1>
      <div className="account-list">
        <div className="account-title">
          <h2>최근 이용 내역</h2>
        </div>
        <div className="account-content">
          {transList.data.map((transaction, index) => (
            <div className="account-container" key={index}>
              <div className="account-line1">
                <div className="trasaction-list">
                  {formatDate(transaction.tradeDate)}
                </div>
              </div>
              <div className="account-line2">
                <div className="trasaction-list">{transaction.name}</div>
                <div className="trasaction-list">
                  {getTransactionTypeText(transaction.transactionType)}
                  &nbsp;:&nbsp;
                  {transaction.transactionAmount} 원
                </div>
              </div>
              <div className="account-line3">
                <div className="trasaction-list">
                  {formatTime(transaction.tradeDate)}
                </div>
                <div className="trasaction-list">
                  잔액 : {transaction.afterBalance} 원
                </div>
              </div>
              <br />
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default AccountTemplate;
