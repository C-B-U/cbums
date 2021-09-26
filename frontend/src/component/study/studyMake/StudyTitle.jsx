import React, { PureComponent } from "react";

class StudyTitle extends PureComponent {
  render() {
    const { title } = this.props;
    return (
      <React.Fragment>
        <div className="main__title">
          <h2>{title}</h2>
        </div>
      </React.Fragment>
    );
  }
}

export default StudyTitle;
