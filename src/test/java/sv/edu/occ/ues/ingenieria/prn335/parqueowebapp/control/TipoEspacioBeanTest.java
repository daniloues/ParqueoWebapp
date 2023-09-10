/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.control;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import sv.edu.occ.ues.ingenieria.prn335.parqueowebapp.app.entity.TipoEspacio;

/**
 *
 * @author alexo
 */
public class TipoEspacioBeanTest {

    EntityManager emGeneradorExcepcion;
    List<TipoEspacio> listaRegistros;

    public TipoEspacioBeanTest() {

        this.listaRegistros = new ArrayList<>();

        this.listaRegistros.add(new TipoEspacio(1));
        this.listaRegistros.get(0).setNombre("chepe");
        this.listaRegistros.add(new TipoEspacio(2));
        this.listaRegistros.add(new TipoEspacio(3));

        emGeneradorExcepcion = new EntityManager() {
            @Override
            public void persist(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T merge(T t) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void remove(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T find(Class<T> type, Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T find(Class<T> type, Object o, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T find(Class<T> type, Object o, LockModeType lmt) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T find(Class<T> type, Object o, LockModeType lmt, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T getReference(Class<T> type, Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void flush() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void setFlushMode(FlushModeType fmt) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public FlushModeType getFlushMode() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void lock(Object o, LockModeType lmt) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void lock(Object o, LockModeType lmt, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void refresh(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void refresh(Object o, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void refresh(Object o, LockModeType lmt) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void refresh(Object o, LockModeType lmt, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void clear() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void detach(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean contains(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public LockModeType getLockMode(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void setProperty(String string, Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Map<String, Object> getProperties() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> TypedQuery<T> createQuery(CriteriaQuery<T> cq) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createQuery(CriteriaUpdate cu) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createQuery(CriteriaDelete cd) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> TypedQuery<T> createQuery(String string, Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createNamedQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> TypedQuery<T> createNamedQuery(String string, Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createNativeQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createNativeQuery(String string, Class type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createNativeQuery(String string, String string1) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public StoredProcedureQuery createNamedStoredProcedureQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String string, Class... types) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String string, String... strings) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void joinTransaction() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean isJoinedToTransaction() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T unwrap(Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Object getDelegate() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void close() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean isOpen() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public EntityTransaction getTransaction() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public EntityManagerFactory getEntityManagerFactory() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public CriteriaBuilder getCriteriaBuilder() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Metamodel getMetamodel() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> EntityGraph<T> createEntityGraph(Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public EntityGraph<?> createEntityGraph(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public EntityGraph<?> getEntityGraph(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
    }

    /**
     * Test of create method, of class TipoEspacioBean.
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("count");
        EntityManager em = Mockito.mock(EntityManager.class);
        CriteriaBuilder cb = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery cq = Mockito.mock(CriteriaQuery.class);
        TypedQuery tq = Mockito.mock(TypedQuery.class);
        Mockito.when(cb.createQuery(Long.class)).thenReturn(cq);
        Mockito.when(em.getCriteriaBuilder()).thenReturn(cb);
        Mockito.when(em.createQuery(cq)).thenReturn(tq);
        Mockito.when(tq.getSingleResult()).thenReturn(Long.valueOf(1));
        TipoEspacioBean cut = new TipoEspacioBean();
        cut.em = em;
        int resultado = cut.count();
        assertEquals(resultado, Long.valueOf(1));

        TipoEspacioBean mockBean = Mockito.mock(TipoEspacioBean.class);
        TipoEspacioBean espia = Mockito.spy(TipoEspacioBean.class);
        espia.em = em;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);

        try {
            espia.count();
        } catch (Exception ex) {
        }

        Mockito.verify(espia, Mockito.times(1)).getEntityManager();

        try {
            cut.em = null;
            cut.count();
            fail("El EntityManager era nulo");
        } catch (Exception ex) {

        }

        //fail("You shall not pass");
    }

    @Test
    public void testFindById() {
        System.out.println("FIndById");

        TipoEspacioBean cut = new TipoEspacioBean();

        EntityManager mockEM = mock(EntityManager.class);
        cut.em = mockEM;

        Object id = 1;

        TipoEspacio objetoPrueba = new TipoEspacio();
        when(mockEM.find(eq(TipoEspacio.class), eq(id))).thenReturn(objetoPrueba);

        TipoEspacio resultado = cut.findById(id);

        TipoEspacioBean mockBean = Mockito.mock(TipoEspacioBean.class);
        TipoEspacioBean espia = Mockito.spy(TipoEspacioBean.class);
        espia.em = mockEM;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);

        try {
            espia.findById(resultado);
        } catch (Exception ex) {
        }

        Mockito.verify(espia, Mockito.times(1)).getEntityManager();

        assertNotNull(resultado);
        assertEquals(objetoPrueba, resultado);

        verify(mockEM).find(eq(TipoEspacio.class), eq(id));

        assertThrows(IllegalArgumentException.class, () -> {
            cut.findById(null);
        });

        cut.em = this.emGeneradorExcepcion;
        assertThrows(IllegalStateException.class, () -> {
            cut.findById(id);
        });

        //fail("You shall not pass");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        EntityManager mockEM = Mockito.mock(EntityManager.class);

        TipoEspacioBean cut = new TipoEspacioBean();
        cut.em = mockEM;

        TipoEspacio eliminado = new TipoEspacio(1);
        cut.delete(eliminado);
        Mockito.verify(mockEM, Mockito.times(1)).remove(ArgumentMatchers.any());
        try {
            cut.delete(null);
            fail("el parametro era nulo");
        } catch (IllegalArgumentException ex) {

        }
        try {
            cut.em = null;
            cut.delete(eliminado);
            fail("el entitymanager era nulo");
        } catch (IllegalStateException ex) {

        }

        TipoEspacioBean mockBean = Mockito.mock(TipoEspacioBean.class);
        TipoEspacioBean espia = Mockito.spy(TipoEspacioBean.class);
        espia.em = mockEM;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);

        try {
            espia.delete(eliminado);
        } catch (Exception ex) {
        }

        Mockito.verify(espia, Mockito.times(1)).getEntityManager();

        //fail("you shall not pass");
    }

    @Test
    public void testModify() {
        System.out.println("Modify");
        TipoEspacioBean cut = new TipoEspacioBean();
        TipoEspacio modificado = new TipoEspacio();

        modificado.setIdTipoEspacio(1);
        modificado.setNombre("chepe");

        EntityManager mockEM = Mockito.mock(EntityManager.class);
        Mockito.when(mockEM.merge(modificado)).thenReturn(modificado);

        TipoEspacioBean mockBean = Mockito.mock(TipoEspacioBean.class);
        TipoEspacioBean espia = Mockito.spy(TipoEspacioBean.class);

        cut.em = mockEM;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);

        espia.em = mockEM;

        try {
            espia.modify(modificado);
        } catch (Exception ex) {
        }

        Mockito.verify(espia, Mockito.times(1)).getEntityManager();

        TipoEspacio resultado = cut.modify(null);
        assertNull(resultado);

        resultado = cut.modify(modificado);
        assertEquals(modificado, resultado);

        cut.em = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.modify(modificado);
        });

        cut.em = this.emGeneradorExcepcion;
        assertThrows(IllegalStateException.class, () -> {
            cut.modify(modificado);
        });

        //fail("you shall not pass");
    }

    @Test
    public void testFindRange() {
        System.out.println("FindRange");
        int first = 0;
        int pageSize = 10;

        TipoEspacioBean cut = new TipoEspacioBean();

        EntityManager em = Mockito.mock(EntityManager.class);
        TypedQuery tq = Mockito.mock(TypedQuery.class);
        Mockito.when(tq.getResultList()).thenReturn(this.listaRegistros);

        CriteriaBuilder mockCb = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery mockCq = Mockito.mock(CriteriaQuery.class);
        Mockito.when(mockCb.createQuery(TipoEspacio.class)).thenReturn(mockCq);

        Mockito.when(em.getCriteriaBuilder()).thenReturn(mockCb);
        Mockito.when(em.createQuery(mockCq)).thenReturn(tq);

        List<TipoEspacio> resultado = cut.FindRange(-1, -1);
        assertTrue(resultado.isEmpty());
        cut.em = em;

        resultado = cut.FindRange(first, pageSize);
        assertNotNull(resultado);
        assertTrue(!resultado.isEmpty());
        assertEquals(listaRegistros.size(), resultado.size());

        cut.em = this.emGeneradorExcepcion;
        assertThrows(IllegalStateException.class, () -> {
            cut.FindRange(0, 10);
        });

        cut.em = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.FindRange(0, 10);
        });

//        fail("You shall not pass");
    }

    @Test
    public void testCreate() {
        System.out.println("create");
        TipoEspacio nuevo = new TipoEspacio();
        nuevo.setNombre("TIPO1");
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        TipoEspacioBean cut = new TipoEspacioBean();
        cut.em = mockEM;
        cut.create(nuevo);

        assertThrows(IllegalArgumentException.class, () -> {
            cut.create(null);
        });

        cut.em = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.create(nuevo);
        });

        cut.em = this.emGeneradorExcepcion;
        assertThrows(IllegalStateException.class, () -> {
            cut.create(nuevo);
        });

        //fail("The test case is a prototype.");
    }

}
