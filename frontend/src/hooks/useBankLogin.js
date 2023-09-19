import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

function useBankLogin() {
  console.log('useBankLogin');
  const [isBankLoggedIn, setIsBankLoggedIn] = useState(false);
  const location = useLocation();

  useEffect(() => {
    const accountNumber = sessionStorage.getItem('accountNumber');
    if (accountNumber) {
      setIsBankLoggedIn(true);
    } else {
      setIsBankLoggedIn(false);
    }
  }, [location]);

  return isBankLoggedIn;
}

export default useBankLogin;
