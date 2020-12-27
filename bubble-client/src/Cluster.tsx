import './Cluster.css';
import React from "react";
import {MDBBtn, MDBCard, MDBCardHeader, MDBCol, MDBModalBody, MDBModalFooter, MDBModalHeader, MDBRow} from "mdbreact";

export interface ClusterProps {
    id: string,
    headline: string,
    size: string,
    articles: any[]
}

const Cluster: React.FC<ClusterProps> = ({id, headline, size, articles}) => {
    const divId = id + 'articles';
    return (
        <MDBCard className='text-center'>
            <MDBCardHeader id={id} className='clusHead cluster'>
                <MDBBtn className='btn-primary-outline'
                        data-toggle='collapse' data-target={'#collapse' + id}>
                    <h2>
                        {headline}
                        <div className='clusterSize'>{size}</div>
                    </h2>
                </MDBBtn>
                <div id={'collapse' + id} className="collapse" data-parent="#clusters">
                    <div className="card-body article-card">
                        <button type="button" className="btn btn-primary" id={'generate' + id}
                                data-toggle="modal" data-target={'#vizModal' + divId}
                                style={{marginBottom: '15px'}}>
                            Show Visualization
                        </button>
                        <div id={'articlesList' + divId} className="articlesWrapper" role="tabpanel">
                            <ul className="list-group list-group-flush" id="' + divId + '">
                                {/*Add Articles*/}
                            </ul>
                        </div>
                        <div id={'visualization' + divId}>
                            <div className="modal fade" id={'vizModal' + divId} tabIndex={-1} role="dialog">
                                <div className="modal-dialog modal-fluid" role="document">
                                    <div className="modal-content">
                                        <MDBModalHeader className="align-self-center">
                                            <h5 className="modal-title" style={{fontWeight: 'bold'}}>
                                                headline</h5>
                                            <button type="button" className="close viz-x" data-dismiss="modal"
                                                    aria-label="Close">
                                                <span aria-hidden="true">&times;</span></button>
                                        </MDBModalHeader>
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
                                            <MDBBtn data-dismiss="modal">Close</MDBBtn>
                                        </MDBModalFooter>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </MDBCardHeader>
        </MDBCard>
    );
}

export default Cluster;