import './Article.css';
import React from "react";
import {MDBBadge, MDBListGroupItem} from "mdbreact";

export interface ArticleProps {
    title: string,
    source: string,
    url: string,
    timePublished: string,
}

/**
 * Given a sourceName, get its clean readable version
 * @param sourceName non-clean sourcename
 * @returns {*} clean readable source name
 */
function cleanSourceName(sourceName: string) {
    return sourceName.replace(/[ .,/#!$%^&*;:{}=\-_`~()]/g,"");
}

const Article: React.FC<ArticleProps> = ({title, source, url, timePublished}) => {
    const articleDate = new Date(
        Date.UTC(parseInt(timePublished.slice(0, 4)),
    parseInt(timePublished.slice(5, 7)) - 1,
            parseInt(timePublished.slice(8, 10)),
            parseInt(timePublished.slice(11, 13)),
            parseInt(timePublished.slice(14, 16))))
    const cleanDate = articleDate.toLocaleTimeString('en-us',
        {hour: 'numeric',
            minute: 'numeric',
            hour12: true,
            timeZoneName:'short'})
    let cleanSource = cleanSourceName(source);
    return (
        <MDBListGroupItem className={cleanSource}>
            <div className="article">
                <h3>
                    <a href={url} target="_blank" rel="noreferrer">
                        {title}
                    </a>
                </h3>
                <MDBBadge color='info' style={{fontSize: '60%'}}>
                    <span>
                        {source}
                        <MDBBadge color='light' className="ml-2">
                            {cleanDate}
                        </MDBBadge>
                    </span>
                </MDBBadge>
            </div>
        </MDBListGroupItem>
    );
}

export default Article;