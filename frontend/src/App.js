import { Route, Routes } from 'react-router-dom';
import './App.css';

import TestPage from './pages/TestPage';

function App() {
  return (
    <Routes>
      <Route path="/test" element={<TestPage />} />
    </Routes>
  );
}

export default App;
