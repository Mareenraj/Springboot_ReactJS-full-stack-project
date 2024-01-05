import React from 'react';
import './App.css';
import Home from "./pages/Home";
import StudentView from "./components/student/StudentView";

function App() {
  return (
    <div className="App">
      <h1> Hello World!</h1>
        <Home/>
        <StudentView/>
    </div>
  );
}

export default App;
