import React from 'react';
import './RegistrationTemplate.css';
import { useParams } from 'react-router-dom';

const RegistrationTemplate = () => {
  //eslint-disable-next-line
  const { accountNumber } = useParams();
  return <div>사용내역 등록 페이지입니다.</div>;
};

export default RegistrationTemplate;
