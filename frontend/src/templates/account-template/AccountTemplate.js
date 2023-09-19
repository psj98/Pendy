import React from 'react';
import './AccountTemplate.css';
import { useParams } from 'react-router-dom';
import useTransList from '../../hooks/useTransList';

const AccountTemplate = () => {
  const { accountNumber } = useParams();
  //eslint-disable-next-line
  const { transList, loading } = useTransList(accountNumber);
  console.log(transList);
  console.log(transList.data);

  return (
    <div>
      <h1>계좌 번호: {accountNumber}</h1>
      <h2>리스트:</h2>
      {transList.data.map((bank, index) => (
        <div key={index} className="signup-input2">
          <input
            type="text"
            className="input"
            placeholder={`은행`}
            variant="outlined"
            value={bank}
          />
          <span className="border"></span>
        </div>
      ))}
    </div>
  );
};

export default AccountTemplate;
