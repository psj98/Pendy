import React, { useState } from 'react';
import './AccountModal.css';

import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { InputLabel, MenuItem } from '@mui/material';

const AccountModal = ({ index, closeModal, handleAccountChange }) => {
  const [accountNumber, setAccountNumber] = useState('');
  const [bankCode, setBankCode] = useState('');

  const handleAccountNumberChange = (e) => {
    setAccountNumber(e.target.value);
  };

  const handleBankCodeChange = (e) => {
    const selectedBankCode = e.target.value;
    setBankCode(selectedBankCode);
  };

  const onSaveClick = () => {
    handleAccountChange(accountNumber, bankCode);
    closeModal();
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <h2>계좌 인증</h2>
        <input
          type="number"
          name="accountNumber"
          placeholder="계좌 번호"
          value={accountNumber}
          onChange={handleAccountNumberChange}
          inputProps={{ inputMode: 'numeric' }}
        />
        <div className="input-container">
          <FormControl fullWidth size="small">
            <InputLabel id="select-label">은행 선택</InputLabel>
            <Select
              labelId="select-label"
              id="simple-select"
              value={bankCode}
              label="은행 선택"
              onChange={handleBankCodeChange}
              fullWidth={true}
            >
              <MenuItem value="">은행 선택</MenuItem>
              <MenuItem value="002">KDB산업은행</MenuItem>
              <MenuItem value="003">IBK기업은행</MenuItem>
              <MenuItem value="004">KB국민은행</MenuItem>
              <MenuItem value="011">NH농협은행</MenuItem>
              <MenuItem value="020">우리은행</MenuItem>
              <MenuItem value="027">씨티은행</MenuItem>
              <MenuItem value="031">DGB대구은행</MenuItem>
              <MenuItem value="032">부산은행</MenuItem>
              <MenuItem value="034">광주은행</MenuItem>
              <MenuItem value="035">제주은행</MenuItem>
              <MenuItem value="037">전북은행</MenuItem>
              <MenuItem value="039">경남은행</MenuItem>
              <MenuItem value="045">새마을금고</MenuItem>
              <MenuItem value="048">신협</MenuItem>
              <MenuItem value="071">우체국예금보험</MenuItem>
              <MenuItem value="081">하나은행</MenuItem>
              <MenuItem value="088">신한은행</MenuItem>
              <MenuItem value="089">케이뱅크</MenuItem>
              <MenuItem value="090">카카오뱅크</MenuItem>
              <MenuItem value="092">토스뱅크</MenuItem>
            </Select>
          </FormControl>
        </div>
        <button onClick={onSaveClick}>등록</button>
      </div>
    </div>
  );
};

export default AccountModal;
