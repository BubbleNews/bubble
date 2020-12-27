import './App.css';
import Header from "./Header";
import {MDBCol, MDBContainer, MDBRow} from "mdbreact";
import SourcesCard from "./SourcesCard";
import React from "react";
import ReclusterCard from "./ReclusterCard";
import ClusterList from "./ClusterList";

function App() {
  return (
    <div className="App">
      <Header />
      <MDBContainer id="graphWrapper">
          <MDBRow>
              <MDBCol lg="2" md='3' id="sourcesColumn">
                <SourcesCard sources={['Source 1', 'Source 2']} />
                <div className="hidden-lg hidden-md hidden-sm"> </div>
              </MDBCol>
              <MDBCol lg="2" md='3' className="order-md-last">
                <ReclusterCard />
                <div className="hidden-lg hidden-md hidden-sm"> </div>
              </MDBCol>
              <MDBCol lg="8" md='6' id="mainWindow">
                <ClusterList clusters={[
                    {
                        id: '1',
                        headline: 'Test Cluster!',
                        size: '5',
                        articles: []
                    }
                ]} chartMessage=''/>
              </MDBCol>
          </MDBRow>
      </MDBContainer>
    </div>
  );
}

export default App;
