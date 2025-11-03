import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SmartTVUI from "./components/SmartTVUI";
import AdminPanel from "./components/AdminPanel";

function App() {
  return (
    <Router basename="/ui">
      <Routes>
        <Route path="/" element={<SmartTVUI />} />
        <Route path="/admin" element={<AdminPanel />} />
      </Routes>
    </Router>
  );
}

export default App;
