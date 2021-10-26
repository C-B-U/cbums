import React, { PureComponent } from "react";
import style from "../../../css/study/study_make/study_make.module.css";

class MakeStudyTitle extends PureComponent {
  render() {
    const { title } = this.props;
    return (
      <React.Fragment>
        <div className={style.main__title}>
          <h2>{title}</h2>
        </div>
      </React.Fragment>
    );
  }
}

export default MakeStudyTitle;
