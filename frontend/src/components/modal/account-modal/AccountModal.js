import React, { useState } from 'react';
import './AccountModal.css';

import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { InputLabel, MenuItem } from '@mui/material';
import handleSendCode from '../../../utils/handleSendCode';
import handleAuthentication from '../../../utils/handleAuthentication';

const AccountModal = ({ index, closeModal, handleAccountChange }) => {
  const [accountNumber, setAccountNumber] = useState('');
  const [bankName, setBankName] = useState('');
  const [bankCode, setBankCode] = useState('');
  const [authCode, setAuthCode] = useState('');
  const [showVerificationInput, setShowVerificationInput] = useState(false);
  const [timer, setTimer] = useState(180);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  //은행 코드 모음
  const bankCodes = {
    KDB산업은행: '002',
    IBK기업은행: '003',
    KB국민은행: '004',
    NH농협은행: '011',
    우리은행: '020',
    씨티은행: '027',
    DGB대구은행: '031',
    부산은행: '032',
    광주은행: '034',
    제주은행: '035',
    전북은행: '037',
    경남은행: '039',
    새마을금고: '045',
    신협: '048',
    우체국예금보험: '071',
    하나은행: '081',
    신한은행: '088',
    케이뱅크: '089',
    카카오뱅크: '090',
    토스뱅크: '092',
  };

  //은행 이름을 코드로 변환
  const onBankNameChange = (e) => {
    const selectedBankName = e.target.value;
    setBankName(selectedBankName);
    const selectedBankCode = bankCodes[selectedBankName];
    setBankCode(selectedBankCode);
  };

  // 1원 인증 요청
  const onSendCodeClick = async (event) => {
    event.preventDefault();
    try {
      const response = await handleSendCode(bankCode, accountNumber);
      if (response.data.code === 1000) {
        console.log('SendCode success');
        alert('계좌로 전송된 예금주명 뒤 숫자 3자리를 확인해주세요');
        setShowVerificationInput(true);
        startTimer();
      } else {
        console.error(response.data.code + ' ' + response.data.message);
        alert('인증에 실패했습니다.');
      }
    } catch (error) {
      console.error('SendCode failed:', error);
      alert('인증 요청에 실패했습니다');
    }
  };

  // 타이머 생성
  const startTimer = () => {
    //시작시간 3분으로 설정
    setTimer(180);
    const countdown = setInterval(() => {
      setTimer((prevTimer) => prevTimer - 1);
    }, 1000);

    setTimeout(() => {
      clearInterval(countdown);
      setShowVerificationInput(false);
    }, 180000);
  };

  // 인증
  const onAuthenticationClick = async (event) => {
    event.preventDefault();
    try {
      const response = await handleAuthentication(accountNumber, authCode);
      if (response.data.code === 1000) {
        console.log('Auth success');
        alert('인증에 성공했습니다.');
        setIsAuthenticated(true);
      } else {
        console.error(response.data.code + ' ' + response.data.message);
        alert('인증에 실패했습니다.');
      }
    } catch (error) {
      console.error('Auth failed:', error);
      alert('인증에 실패했습니다');
    }
  };

  // 계좌 등록
  const onSaveClick = async () => {
    if (isAuthenticated) {
      await handleAccountChange(index, accountNumber, bankName);
      await closeModal();
    } else {
      alert('인증을 먼저 완료해주세요');
    }
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <div className="modal-header">
          <div className="modal-title">
            <h2>계좌 인증</h2>
          </div>
          <div className="close-button-container">
            <button onClick={closeModal}>x</button>
          </div>
        </div>
        <div className="input-container">
          <h3>은행 선택</h3>
          <FormControl fullWidth size="small">
            <InputLabel id="select-label">은행 선택</InputLabel>
            <Select
              labelId="select-label"
              id="simple-select"
              value={bankName}
              label="은행 선택"
              onChange={(e) => onBankNameChange(e)}
              fullWidth={true}
            >
              <MenuItem value="KDB산업은행">KDB산업은행</MenuItem>
              <MenuItem value="IBK기업은행">IBK기업은행</MenuItem>
              <MenuItem value="KB국민은행">KB국민은행</MenuItem>
              <MenuItem value="NH농협은행">NH농협은행</MenuItem>
              <MenuItem value="우리은행">우리은행</MenuItem>
              <MenuItem value="씨티은행">씨티은행</MenuItem>
              <MenuItem value="DGB대구은행">DGB대구은행</MenuItem>
              <MenuItem value="부산은행">부산은행</MenuItem>
              <MenuItem value="광주은행">광주은행</MenuItem>
              <MenuItem value="제주은행">제주은행</MenuItem>
              <MenuItem value="전북은행">전북은행</MenuItem>
              <MenuItem value="경남은행">경남은행</MenuItem>
              <MenuItem value="새마을금고">새마을금고</MenuItem>
              <MenuItem value="신협">신협</MenuItem>
              <MenuItem value="우체국예금보험">우체국예금보험</MenuItem>
              <MenuItem value="하나은행">하나은행</MenuItem>
              <MenuItem value="신한은행">신한은행</MenuItem>
              <MenuItem value="케이뱅크">케이뱅크</MenuItem>
              <MenuItem value="카카오뱅크">카카오뱅크</MenuItem>
              <MenuItem value="토스뱅크">토스뱅크</MenuItem>
            </Select>
          </FormControl>
          <h3>계좌 번호</h3>
          <div className="modal-input">
            <div className="modal-input-part1">
              <input
                type="number"
                name="accountNumber"
                placeholder="-를 제외하고 입력"
                value={accountNumber}
                onChange={(e) => setAccountNumber(e.target.value)}
              />
            </div>
            <div className="modal-input-part2">
              <button onClick={onSendCodeClick}>인증 요청</button>
            </div>
          </div>
        </div>
        {showVerificationInput && (
          <div className="input-container">
            <h3>인증번호 입력</h3>
            <div className="modal-input">
              <div className="modal-input-part1">
                <input
                  type="number"
                  name="authCode"
                  placeholder="인증번호를 입력하세요"
                  value={authCode}
                  onChange={(e) => setAuthCode(e.target.value)}
                />
              </div>
              <div className="modal-input-part2">
                <button onClick={onAuthenticationClick}>인증</button>
              </div>
            </div>
            <div className="timer-text">{timer}초</div>
          </div>
        )}
        <div className="modal-button">
          <button onClick={onSaveClick}>등록</button>
        </div>
      </div>
    </div>
  );
};

export default AccountModal;
