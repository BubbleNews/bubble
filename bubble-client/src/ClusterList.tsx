import './ClusterList.css';
import React from "react";
import Cluster, {ClusterProps} from "./Cluster";

interface ClusterListProps {
    chartMessage: string,
    clusters: ClusterProps[]
}

const ClusterList: React.FC<ClusterListProps> = ({chartMessage, clusters}) => {
    return (
        <div className='clusterList'>
            <div id="chartMessageWrapper">
                <div id="chartMessage">{chartMessage}</div>
            </div>
            {(!chartMessage && clusters.length === 0) &&
                <div id="mainLoader" className="spinner-border text-primary" role="status">
                    <span className="sr-only">Loading...</span>
                </div>
            }
            <div className="accordion" id="clusters">
                {clusters.map((cluster, index) =>
                <Cluster {...cluster} key={index}/>)}
            </div>
            <div className="hidden-lg hidden-md hidden-sm"> </div>
        </div>
    );
}

export default ClusterList;