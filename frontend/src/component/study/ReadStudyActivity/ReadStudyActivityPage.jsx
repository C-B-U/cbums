import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournalReading/study_workJournalReading.module.css";
import ReadStudyActivityList from "./ReadStudyActivityList";
import ReadStudyActivityTable from "./ReadStudyActivityTable";
import ReadStudyActivityTitle from "./ReadStudyActivityTitle";

class ReadStudyActivityPage extends PureComponent {
    render() {
        return (
            <React.Fragment>
            <main>
                <div className={style.main_wrap}>
                    <ReadStudyActivityTitle studyName="JAVA1"/>
                    <ReadStudyActivityTable />
                    <ReadStudyActivityList />
                </div>
            </main>
            </React.Fragment>
        );
    }
}

export default ReadStudyActivityPage;
