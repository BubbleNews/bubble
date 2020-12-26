import './SourcesCard.css';
import React from "react";
import {MDBBtn, MDBCard, MDBCardBody, MDBCardHeader} from "mdbreact";

interface SourcesCardProps {
    sources: string[]
}

const SourcesCard: React.FC<SourcesCardProps> = ({sources}) => {
    return (
        <MDBCard className="sticky-top sidePanel sourcesWrapper">
            <MDBCardHeader><h4>Toggle Sources</h4></MDBCardHeader>
            <MDBCardBody className="sources">
                {sources.map((source) =>
                    <MDBBtn color='success' className="sourceToggle">{source}</MDBBtn>
                )}
            </MDBCardBody>
        </MDBCard>
    );
}

export default SourcesCard;