//로그인 여부 판단
import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

function useLogin() {
  console.log('useLogin');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const location = useLocation();

  useEffect(() => {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, [location]);

  return isLoggedIn;
}

export default useLogin;
