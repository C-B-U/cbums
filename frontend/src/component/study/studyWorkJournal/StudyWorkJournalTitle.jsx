import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournal/study_workJournal.module.css";
import StudyWorkJournalDetails from "./StudyWorkJournalDetails";


class StudyWorkJournalTitle extends PureComponent {
    render() {
        return (
            <React.Fragment>
                <div className={style['main__title-wrap']}>
                    <div className={style.main__title}>
                        <h2>{this.props.studyName}</h2>  {/*--스터디명--*/}
                    </div>
                    <StudyWorkJournalDetails />
                </div>
            </React.Fragment>
        );
    }
}

export default StudyWorkJournalTitle;
