import './Header.css';
import question from './question.png';
import Particles from 'react-particles-js';
import particlesSpecs from './particles.json'
import {MDBBtn, MDBCol, MDBRow} from "mdbreact";

function Header() {
    return (
        <div className="header">
            <Particles id="particles-js" params={particlesSpecs}/>
            <button id="question-button" className="question-button"><img src={question} alt="question"/></button>
            <div id="titleWrapper">
                <h1>Bubble</h1>
            </div>

            <div id="dateWrapper">
                <div id="dateAndButton">
                    <MDBRow>
                        <input type="date" id="date" readOnly />
                    </MDBRow>
                    <MDBRow>
                        <MDBCol className="my-auto">
                            <MDBBtn color="primary" size="lg" id="dateButton" type="button"
                                    style={{ marginTop: '1em' }}>Show
                            </MDBBtn>
                        </MDBCol>
                    </MDBRow>
                </div>
            </div>
        </div>
    );
}

export default Header;