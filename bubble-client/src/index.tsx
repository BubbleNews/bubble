import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';
// import 'mdb-ui-kit/css/mdb.min.css';
// import 'mdb-ui-kit/js/mdb.min.js';
// import jquery from 'mdbootstrap/js/jquery.js';
// import 'mdbootstrap/js/popper.min.js';
// import 'mdbootstrap/js/bootstrap.min.js';
// import 'mdbootstrap/js/mdb.min.js'
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
