import './Cluster.css';
import React, {useState} from "react";
import {MDBBtn, MDBCard, MDBCardHeader, MDBCol, MDBModal, MDBModalBody,
    MDBModalFooter, MDBRow } from "mdbreact";
import {Accordion, Button} from "react-bootstrap";
import Article, {ArticleProps} from "./Article";

export interface ClusterProps {
    id: string,
    headline: string,
    size: string,
    articles: ArticleProps[]
}

const Cluster: React.FC<ClusterProps> = ({id, headline, size, articles}) => {
    const divId = id + 'articles';
    const [showViz, setShowViz] = useState(false);
    const vizToggle = () => setShowViz(!showViz);
    return (
        <MDBCard className='text-center'>
            <MDBCardHeader id={id} className='clusHead cluster'>
                <Accordion.Toggle as={Button}
                    className='btn-primary-outline'
                        eventKey={'collapse' + id}>
                    <h2>
                        {headline}
                        <div className='clusterSize'>{size}</div>
                    </h2>
                </Accordion.Toggle>
                <Accordion.Collapse eventKey={'collapse' + id} data-parent="#clusters">
                    <div className="card-body article-card">
                        <button type="button"
                                onClick={vizToggle}
                                className="btn btn-primary" id={'generate' + id}
                                data-toggle="modal"
                                style={{marginBottom: '15px'}}>
                            Show Visualization
                        </button>
                        <div id={'articlesList' + divId} className="articlesWrapper" role="tabpanel">
                            <ul className="list-group list-group-flush" id="' + divId + '">
                                {articles.map(
                                    (article, index) =>
                                        <Article {...article} key={index} />)}
                            </ul>
                        </div>
                        <div id={'visualization' + divId}>
                            <MDBModal fade isOpen={showViz} toggle={vizToggle} centered
                                      tabIndex="-1" role="dialog"
                                      inline={false} noClickableBodyWithoutBackdrop={false}
                                      overflowScroll={false}>
                                <div className="modal-header align-self-center">
                                    <h5 className="modal-title" style={{fontWeight: 'bold'}}>
                                        headline</h5>
                                    <button type="button"
                                            className="close viz-x"
                                            onClick={vizToggle}
                                            aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <MDBModalBody>
                                    <div className={'diagram' + id}>
                                        <MDBRow className="justify-content-md-center">'
                                            <MDBBtn color='info' className={'entityBut' + id}>
                                                Key Word
                                            </MDBBtn>
                                            <MDBBtn color='info' className={'textBut' + id}>
                                                Text
                                            </MDBBtn>
                                            <MDBBtn color='info' className={'titleBut' + id}>
                                                Title
                                            </MDBBtn>
                                            <MDBBtn color='info' className={'AllBut' + id}>
                                                All
                                            </MDBBtn>
                                        </MDBRow>
                                        <div className={'row spinner-border text-primary spin' + id}
                                             role="status">
                                            <span className="sr-only">Loading...</span></div>
                                        <MDBRow className="charts">
                                            <MDBCol lg='6' className="chord-chart" id={'chord' + id} />
                                            <div className="box-plot" id={'box' + id}/>
                                            <MDBCol lg='6' className="bar-chart align-middle" id={'bar' + id} />
                                        </MDBRow>
                                        <MDBBtn className="btn-outline-blue-grey btn-sm waves-effect"
                                                style={{marginBottom: '10px'}}
                                                data-toggle="modal" data-target="#vizInfoModal">
                                            Info
                                        </MDBBtn>
                                    </div>
                                </MDBModalBody>
                                <MDBModalFooter>
                                    <MDBBtn onClick={vizToggle} color='primary'>Close</MDBBtn>
                                </MDBModalFooter>
                            </MDBModal>
                        </div>
                    </div>
                </Accordion.Collapse>
            </MDBCardHeader>
        </MDBCard>
    );
}

export default Cluster;