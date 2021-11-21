import React, { PureComponent } from "react";
import style from '../../../css/study/study_workJournal/study_workJournal.module.css';
import StudyWorkJournalFooter from "./StudyWorkJournalFooter";
import StudyWorkJournalTable from "./StudyWorkJournalTable";
import StudyWorkJournalTitle from "./StudyWorkJournalTitle";
class StudyWorkJournalPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <main>
          <div className={style.main_wrap}>
                    <StudyWorkJournalTitle studyName="JAVA 스터디" />
                    <StudyWorkJournalTable />
                    <StudyWorkJournalFooter />
          </div>
        </main>
      </React.Fragment>
    );
  }
}

export default StudyWorkJournalPage;
/*
                
                    
                </div>
          */