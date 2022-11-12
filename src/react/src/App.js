import {Route, Routes} from "react-router";
import Login from "./view/Login";
import Register from "./view/Register";
import Main from "./view/Main";


function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/login" element={<Login />} />
        <Route path="/about" element={<Register />} />
      </Routes>
    </div>
  );
}

export default App;
