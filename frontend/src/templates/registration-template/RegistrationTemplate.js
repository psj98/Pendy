import React, { useState } from 'react';
import './RegistrationTemplate.css';
import { useNavigate, useParams } from 'react-router-dom';
import handleBankRegist from '../../utils/handleBankRegist';

const RegistrationTemplate = () => {
  const { accountNumber } = useParams();
  const balance = sessionStorage.getItem('balance');
  const [state, setState] = useState({
    categoryId: '',
    transactionName: '',
    transactionAmount: '',
    transactionType: '',
  });
  const navigate = useNavigate();

  //등록 버튼
  const onRegistButtonClick = async (event) => {
    event.preventDefault();
    try {
      const response = await handleBankRegist(
        accountNumber,
        state.categoryId,
        state.transactionName,
        state.transactionAmount,
        state.transactionType,
        balance,
      );
      if (response.data.code === 1000) {
        console.log('Regist success');
        sessionStorage.setItem('balance', response.data.data.balance);
        alert('등록에 성공하셨습니다.');
        navigate(`/bank/account/${accountNumber}`);
      } else {
        console.error(response.data.code + ' ' + response.data.message);
        alert('등록에 실패하셨습니다');
      }
    } catch (error) {
      console.error('Regist failed');
      alert('등록에 실패하셨습니다');
    }
  };

  return (
    <div className="registration">
      <p className="registration-title">사용내역 등록</p>
      <div className="registration-input">
        <input
          className="input"
          type="text"
          placeholder="거래내역 명"
          variant="outlined"
          value={state.transactionName}
          onChange={(e) =>
            setState({ ...state, transactionName: e.target.value })
          }
        />
        <span className="border"></span>
      </div>
      <div className="registration-input">
        <input
          className="input"
          type="number"
          placeholder="거래 금액"
          variant="outlined"
          value={state.transactionAmount}
          onChange={(e) =>
            setState({ ...state, transactionAmount: e.target.value })
          }
        />
        <span className="border"></span>
      </div>
      <div className="registration-input">
        <input
          className="input"
          type="number"
          placeholder="거래 종류"
          variant="outlined"
          value={state.transactionType}
          onChange={(e) =>
            setState({ ...state, transactionType: e.target.value })
          }
        />
        <span className="border"></span>
      </div>
      <div className="registration-input">
        <input
          className="input"
          type="number"
          placeholder="분류 종류"
          variant="outlined"
          value={state.categoryId}
          onChange={(e) => setState({ ...state, categoryId: e.target.value })}
        />
        <span className="border"></span>
      </div>
      <br />
      <div className="registration-input">
        <button className="registration-button" onClick={onRegistButtonClick}>
          등록하기
        </button>
      </div>
    </div>
  );
};

export default RegistrationTemplate;
