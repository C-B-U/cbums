import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournalWriting/WriteStudyActivity.module.css";
import WriteStudyActivityTable from "./WriteStudyActivityTable";
import WriteStudyActivityTitle from "./WriteStudyActivityTitle";
class WriteStudyActivityPage extends PureComponent {
    render() {
        return (
            <React.Fragment>
                <main>
                    <div className={style.main_wrap}>
                        <WriteStudyActivityTitle studyName="Java스터디" />
                        <WriteStudyActivityTable />
                    </div>
                </main>
            </React.Fragment>
        );
    }
}

export default WriteStudyActivityPage;
