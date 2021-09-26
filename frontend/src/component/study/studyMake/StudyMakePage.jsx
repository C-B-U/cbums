import React, { PureComponent } from "react";
import StudyTitle from "./StudyTitle";
import "../../../css/study/study_make/study_make.css";
import StudySlide from "./StudySlide";

class StudyMakePage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <main>
          <div className="main_wrap">
            <StudyTitle title="스터디 개설" />
            <StudySlide />
          </div>
        </main>
      </React.Fragment>
    );
  }
}

export default StudyMakePage;
