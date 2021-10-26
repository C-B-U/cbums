import React, { PureComponent } from "react";
import MakeStudyTitle from "./MakeStudyTitle";
import style from "../../../css/study/study_make/study_make.module.css";
import MakeStudyContentList from "./MakeStudyContentList";

class StudyMakePage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <main>
          <div className={style.main_wrap}>
            <MakeStudyTitle title="스터디 개설" />
            <MakeStudyContentList />
          </div>
        </main>
      </React.Fragment>
    );
  }
}

export default StudyMakePage;
