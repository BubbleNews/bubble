import './ReclusterCard.css';
import React from "react";
import {MDBBtn, MDBCard, MDBCardBody, MDBCardHeader} from "mdbreact";

interface ReclusterCardProps {}

const ReclusterCard: React.FC<ReclusterCardProps> = () => {
    return (
        <MDBCard id="reclusterWrapper" className="sticky-top sidePanel">
            <MDBCardHeader><h4>Grouping Parameters</h4>
                <MDBBtn className="btn-outline-blue-grey btn-sm waves-effect"
                        style={{ marginBottom: '10px' }} data-toggle="modal" data-target="#infoModal">
                    Info
                </MDBBtn>
            </MDBCardHeader>
            <MDBCardBody id="clusterParameters">
                <form id="reclusterParams">
                    <div className="form-group">
                        <label htmlFor="textWeight">Text Weight</label>
                        <input name="textWeight" id="textWeight" className="custom-range" type="range" min="0" max="1"
                               value=".5" step="0.01" />
                        <label htmlFor="titleWeight">Title Weight</label>
                        <input name="titleWeight" id="titleWeight" className="custom-range" type="range" min="0"
                               max="1"
                               value=".5" step="0.01" />
                        <label htmlFor="entityWeight">Key Word Weight</label>
                        <input name="entityWeight" id="entityWeight" className="custom-range" type="range"
                               min="0"
                               max="1"
                               value=".5" step="0.01" />

                        <label htmlFor="numArticles">Maximum Number of Articles</label>
                        <input name="numArticles" type="number" className="form-control" id="numArticles"
                               min="0"
                               max="200"
                               value="100" style={{ marginBottom: '5px' }} />
                        <MDBBtn size='sm' color='primary'
                                type="submit">
                            Regroup
                        </MDBBtn>
                        <MDBBtn color='blue-grey' size='sm' id="resetButton">
                            Reset
                        </MDBBtn>
                    </div>
                </form>
            </MDBCardBody>
        </MDBCard>
    );
}

export default ReclusterCard;