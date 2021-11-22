import React, { PureComponent } from "react";
import style from '../../../css/study/study_workJournal/study_workJournal.module.css';
import StudyActivityFooter from "./StudyActivityFooter";
import StudyActivityTable from "./StudyActivityTable";
import StudyActivityTitle from "./StudyActivityTitle";
class StudyActivityPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <main>
          <div className={style.main_wrap}>
            <StudyActivityTitle studyName="JAVA 스터디" />
            <StudyActivityTable />
            <StudyActivityFooter />
          </div>
        </main>
      </React.Fragment>
    );
  }
}

export default StudyActivityPage;