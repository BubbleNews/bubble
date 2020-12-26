import './SourcesCard.css';
import React from "react";
import {MDBCard, MDBCardBody, MDBCardHeader} from "mdbreact";

interface SourcesCardProps {
    sources: string[]
}

const SourcesCard: React.FC<SourcesCardProps> = ({sources}) => {
    return (
        <MDBCard id="sourcesWrapper" class="sticky-top sidePanel">
            <MDBCardHeader><h4>Toggle Sources</h4></MDBCardHeader>
            <MDBCardBody id="sources">
                {sources.map((source) =>
                    <button type="button" className="btn btn-success
                    sourceToggle">{source}</button>
                )}
            </MDBCardBody>
        </MDBCard>
    );
}

export default SourcesCard;