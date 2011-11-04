/**
 * <copyright>
 * </copyright>
 *

 */
package cruise.umple.umple;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>conditional Or Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cruise.umple.umple.conditionalOrExpression#getExpression1 <em>Expression1</em>}</li>
 *   <li>{@link cruise.umple.umple.conditionalOrExpression#getExpressionRest <em>Expression Rest</em>}</li>
 * </ul>
 * </p>
 *
 * @see cruise.umple.umple.UmplePackage#getconditionalOrExpression()
 * @model
 * @generated
 */
public interface conditionalOrExpression extends EObject
{
  /**
   * Returns the value of the '<em><b>Expression1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression1</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression1</em>' containment reference.
   * @see #setExpression1(conditionalAndExpression)
   * @see cruise.umple.umple.UmplePackage#getconditionalOrExpression_Expression1()
   * @model containment="true"
   * @generated
   */
  conditionalAndExpression getExpression1();

  /**
   * Sets the value of the '{@link cruise.umple.umple.conditionalOrExpression#getExpression1 <em>Expression1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression1</em>' containment reference.
   * @see #getExpression1()
   * @generated
   */
  void setExpression1(conditionalAndExpression value);

  /**
   * Returns the value of the '<em><b>Expression Rest</b></em>' containment reference list.
   * The list contents are of type {@link cruise.umple.umple.conditionalAndExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression Rest</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression Rest</em>' containment reference list.
   * @see cruise.umple.umple.UmplePackage#getconditionalOrExpression_ExpressionRest()
   * @model containment="true"
   * @generated
   */
  EList<conditionalAndExpression> getExpressionRest();

} // conditionalOrExpression