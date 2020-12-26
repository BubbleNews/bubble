import './App.css';
import Header from "./Header";
import {MDBCol, MDBContainer, MDBRow} from "mdbreact";
import SourcesCard from "./SourcesCard";
import React from "react";

function App() {
  return (
    <div className="App">
      <Header />
      <MDBContainer id="graphWrapper">
          <MDBRow>
              <MDBCol lg="2" id="sourcesColumn">
                <SourcesCard sources={['Source 1', 'Source 2']} />
                <div className="hidden-lg hidden-md hidden-sm"> </div>
              </MDBCol>
              <MDBCol lg="2" className="order-lg-last">

              </MDBCol>
              <MDBCol lg="8" id="mainWindow">

              </MDBCol>
          </MDBRow>
      </MDBContainer>
    </div>
  );
}

export default App;
