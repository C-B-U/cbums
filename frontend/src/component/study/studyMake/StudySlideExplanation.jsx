import React, { PureComponent } from "react";
import style from "../../../css/study/study_make/study_make.module.css";

class StudySlideExplanation extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style["main__slide-explanation"]}>
          부가설명
          <br />
          <textarea
            className={style["main__slide-explanation-textarea"]}
            name="explanation"
            required
            wrap="hard"
          ></textarea>
        </div>
      </React.Fragment>
    );
  }
}

export default StudySlideExplanation;
