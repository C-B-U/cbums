import React, { PureComponent } from "react";

class StudySlideInformation extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <form action="#" method="POST" name="study_info">
          <div className="main__studymake-information">
            <div className="main__slide-studyname">
              스터디명
              <input
                className="studymake_input"
                type="text"
                name="studyname"
                autoComplete="off"
              />
            </div>
            <div className="main__slide-duration">
              진행 기간
              <input
                className="studymake_input"
                type="date"
                name="duration-first"
              />
              ~
              <input
                className="studymake_input"
                type="date"
                name="duration-last"
              />
            </div>
            <div className="main__slide-number">
              모집 인원
              <input
                className="studymake_input"
                type="text"
                name="number-of-study"
                autoComplete="off"
              />
              명
            </div>
          </div>
        </form>
      </React.Fragment>
    );
  }
}

export default StudySlideInformation;
