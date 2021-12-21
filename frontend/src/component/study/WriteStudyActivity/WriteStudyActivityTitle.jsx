import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournalWriting/WriteStudyActivity.module.css";

class WriteStudyActivityTitle extends PureComponent {
    render() {
        return (
            <React.Fragment>
                <div className={style["main__title-wrap"]}>
                    <div className={style.main__title}>
                        <h2 style={{fontSize:"1.5em"}}>{this.props.studyName}</h2>  
                    </div>  
                </div>
            </React.Fragment>
        );
    }
}

export default WriteStudyActivityTitle;
