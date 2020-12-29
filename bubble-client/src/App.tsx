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
                        articles: [
                            {
                                title: 'First Article is This',
                                source: 'Politico',
                                url: 'https://www.politico.com/news/2020/05/01/coronavirus-fundraising-zoom-227456',
                                timePublished: '2020-05-01 08:30:54',
                            },
                            {
                                title: 'ESPN’s Alex Smith special is ‘authentic’ look at QB’s recovery from gruesome injury',
                                source: 'The Washington Post',
                                url: 'https://www.washingtonpost.com/sports/2020/05/01/espns-alex-smith-special-is-an-authentic-look-qbs-recovery-gruesome-injury/',
                                timePublished: '2020-05-01 08:30:46',
                            }
                        ]
                    },
                    {
                        id: '2',
                        headline: 'Another News Story!',
                        size: '4',
                        articles: [
                            {
                                title: 'NASA Names Firms In Competition To Build Next-Gen Lunar Lander : NPR',
                                source: 'Npr.org',
                                url: 'https://www.npr.org/2020/05/01/848909045/nasa-names-firms-in-competition-to-build-next-gen-lunar-lander',
                                timePublished: '2020-05-01 08:08:00',
                            }
                        ]
                    }
                ]} chartMessage=''/>
              </MDBCol>
          </MDBRow>
      </MDBContainer>
    </div>
  );
}

export default App;
